import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
from plyer import notification
from datetime import datetime

# ===================== SETTINGS =====================
CONTACT_NAME = "Pinki"   # Change to your contact's exact name
CHECK_INTERVAL = 1       # Check every 1 second
LOG_TO_FILE = True
LOG_FILE = "whatsapp_log.txt"
# ====================================================

def log(message):
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    full_msg = f"[{timestamp}] {message}"
    print(full_msg)
    if LOG_TO_FILE:
        with open(LOG_FILE, "a") as f:
            f.write(full_msg + "\n")

def notify(title, message):
    try:
        notification.notify(
            title=title,
            message=message,
            app_name="WhatsApp Tracker",
            timeout=6
        )
    except Exception as e:
        print(f"Notification error: {e}")

def start_tracker():
    print("=" * 45)
    print("   WhatsApp Online Tracker - Mac")
    print("=" * 45)

    options = webdriver.ChromeOptions()
    options.add_argument("--start-maximized")
    options.add_argument("--disable-notifications")
    options.add_argument("--user-data-dir=/tmp/whatsapp-chrome-profile")

    driver = webdriver.Chrome(
        service=Service(ChromeDriverManager().install()),
        options=options
    )

    driver.get("https://web.whatsapp.com")
    print("\n👉 Scan QR code if needed, then press Enter...")
    input()

    print("⏳ Waiting for WhatsApp to load fully...")
    time.sleep(8)

    print(f"\n🔍 Searching for: {CONTACT_NAME}")

    try:
        # Find search box
        search_box = WebDriverWait(driver, 20).until(
            EC.presence_of_element_located((By.XPATH, '//input[@placeholder="Search or start a new chat"]'))
        )
        search_box.click()
        time.sleep(1)
        search_box.send_keys(CONTACT_NAME)
        time.sleep(3)

        # Find and click contact
        contact_xpaths = [
            f'//span[@title="{CONTACT_NAME}"]',
            f'//span[contains(@title,"{CONTACT_NAME}")]',
            f'//div[@role="listitem"]//span[contains(@title,"{CONTACT_NAME}")]',
        ]

        contact = None
        for xpath in contact_xpaths:
            try:
                contact = WebDriverWait(driver, 5).until(
                    EC.element_to_be_clickable((By.XPATH, xpath))
                )
                if contact:
                    print(f"✅ Contact found!")
                    break
            except:
                continue

        if not contact:
            print(f"\n❌ Could not find '{CONTACT_NAME}'")
            print("📋 Contacts visible in search results:")
            try:
                all_spans = driver.find_elements(By.XPATH, '//span[@title]')
                for span in all_spans[:10]:
                    title = span.get_attribute("title")
                    if title:
                        print(f"   → {title}")
            except:
                pass
            driver.quit()
            return

        contact.click()
        time.sleep(3)
        print(f"✅ Tracking started for: {CONTACT_NAME}")
        log(f"Tracking started for {CONTACT_NAME}")

    except Exception as e:
        print(f"❌ Error: {e}")
        driver.quit()
        return

    last_status = ""

    print(f"\n👀 Watching status every {CHECK_INTERVAL} second(s)...\n")

    while True:
        try:
            # ✅ FIXED: Only search inside header to avoid picking up chat messages
            status = ""
            try:
                header = driver.find_element(By.XPATH, '//header')
                spans = header.find_elements(By.XPATH, './/span')
                for span in spans:
                    text = span.text.strip().lower()
                    if "online" in text or "last seen" in text or "typing" in text:
                        status = text
                        break
            except:
                pass

            # Show live status in terminal
            if status:
                print(f"📡 Status: {status}        ", end="\r")
            else:
                print(f"📡 Status: (not visible)        ", end="\r")

            # Came online
            if "online" in status and last_status != "online":
                log(f"🟢 {CONTACT_NAME} came ONLINE")
                notify("WhatsApp Tracker", f"{CONTACT_NAME} is now Online!")

            # Went offline
            elif "online" not in status and last_status == "online":
                log(f"🔴 {CONTACT_NAME} went OFFLINE — {status}")
                notify("WhatsApp Tracker", f"{CONTACT_NAME} went offline.")

            # Typing
            if "typing" in status and "typing" not in last_status:
                log(f"✍️  {CONTACT_NAME} is TYPING...")

            # Update last status
            last_status = "online" if "online" in status else status

        except Exception as e:
            print(f"\n⚠️ Error reading status: {e}")

        time.sleep(CHECK_INTERVAL)

if __name__ == "__main__":
    start_tracker()