# Remote TV Panasonic (IR Blaster) 📺

แอปพลิเคชัน Android สำหรับใช้มือถือที่มีเซ็นเซอร์อินฟราเรด (IR Blaster) เป็นรีโมทคอนโทรลสำหรับทีวี **Panasonic Viera Smart TV (รุ่นปี 2012-2016)** โดยเฉพาะ ออกแบบมาเพื่อแก้ปัญหาแอปครอบจักรวาลที่มีโฆษณากวนใจ และทำงานได้แม่นยำ 100% ตามมาตรฐานโปรโตคอล Kaseikyo ของ Panasonic

## ✨ ฟีเจอร์หลัก (Features)
*   **ใช้งานง่าย ไร้โฆษณา:** เปิดแอปแล้วกดใช้งานได้ทันที ไม่ต้องสุ่มหารุ่นทีวี ไม่ต้องดูโฆษณา
*   **ความแม่นยำสูง (High Precision):** 
    *   ใช้คลื่นความถี่ (Carrier Frequency) **36,700 Hz** ตรงตามสเปกของตัวรับสัญญาณ Panasonic เป๊ะๆ
    *   สร้างรหัส IR 48-bit แบบ **MSB-first (อ่านข้อมูลจากซ้ายไปขวา)** เพื่อให้เข้ากันได้กับทีวี Viera รุ่นเก่า
    *   ระยะห่างของสัญญาณ (Frame gap) อยู่ที่ **74,767 µs** พอดี
*   **คำนวณ Checksum แม่นยำ:** รองรับการคำนวณ XOR Checksum ในไบต์สุดท้ายของสัญญาณ ป้องกันไม่ให้ทีวีปฏิเสธคำสั่ง

## 🛠️ ข้อมูลทางเทคนิค (Technical Details)
*   **Platform:** Android (Kotlin)
*   **Hardware Requirement:** สมาร์ทโฟน Android ที่มีเซ็นเซอร์ส่งสัญญาณอินฟราเรด (IR Transmitter) เช่น Xiaomi, Poco, Redmi, ฯลฯ
*   **Protocol:** Kaseikyo (48-bit)
*   **Supported Remotes:** ทดสอบแล้วว่าสามารถใช้แทนรีโมทรุ่น **N2QAYB000818** ได้สมบูรณ์
*   **IR Hex Codes (MSB-first):**
    *   `Volume UP`: 0x40 0x04 0x01 0x00 0x04 0x05
    *   `Volume DOWN`: 0x40 0x04 0x01 0x00 0x84 0x85

## 🚀 การติดตั้ง (Installation)
1. **ติดตั้งผ่าน GitHub Actions:** ไปที่แท็บ [Actions](https://github.com/son11930/Remote_Tv_Panasonic/actions) เพื่อดาวน์โหลดไฟล์ `.apk` ล่าสุดที่ระบบ Build ให้โดยอัตโนมัติ
2. **ติดตั้งสำหรับนักพัฒนา:** 
   * Clone โปรเจกต์นี้ลงในเครื่อง `git clone https://github.com/son11930/Remote_Tv_Panasonic.git`
   * เปิดโปรเจกต์ด้วย **Android Studio**
   * กดรัน (Run) หรือ Build APK ผ่านเมนู Build > Build Bundle(s) / APK(s)

## 📝 บันทึกการอัปเดต (Changelog)
*   **v1.0.1** - แก้ไขบั๊กทีวีรับคำสั่งเบิ้ล (ลดการส่งสัญญาณเหลือ 1 Frame แบบไม่ซ้ำ)
*   **v1.0.0** - เปลี่ยนไปใช้โครงสร้าง Kaseikyo แบบ MSB-first แทน LSB-first ทำให้ทีวีอ่านคำสั่งออก
*   **v0.9.0-beta** - เวอร์ชันทดสอบแรกที่สามารถเชื่อมต่อกับทีวีได้ (แต่มีบั๊กเสียงเบิ้ล)

---
*พัฒนาเพื่อใช้งานส่วนตัว และเป็น Open Source สำหรับทุกคนที่เจอปัญหารีโมท Panasonic พัง/หาย*
