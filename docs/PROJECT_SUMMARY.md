# Project Architecture and Technical Summary
**Project:** Remote TV Panasonic (IR Blaster)
**Target Hardware:** Panasonic Viera Smart TV (Model equivalent to remote N2QAYB000818)

## 1. Core Problem Addressed
Generic universal remote apps often contain intrusive ads or incorrect code mappings. This project builds a dedicated, lightweight, ad-free Android IR blaster application specifically tuned for Panasonic Viera Smart TVs.

## 2. Technical Discoveries & IR Protocol Specification
During the development of this project, we discovered several critical hardware-specific constraints for the target TV:

* **Protocol:** Kaseikyo 48-bit (Standard Panasonic protocol)
* **Carrier Frequency:** `36,700 Hz` (Many generic apps use `37,000` or `38,000`, which leads to high packet rejection rates).
* **Frame Gap (Space):** `74,767 µs`
* **Bit Order (CRITICAL):** While most LIRC and IR databases represent Panasonic Kaseikyo as LSB-first (Least Significant Bit first), **the target TV requires MSB-first transmission**. Sending standard LSB-first resulted in the TV's receiver LED blinking (acknowledging the carrier frequency) but rejecting the command.
* **Frame Repetition:** The target TV executes commands instantaneously upon receiving a single valid frame. Generic universal blasters usually send 3 repeats per button press, which caused a "Double Volume Jump" bug on this specific TV. **We restricted the transmission to exactly 1 frame per button press.**

## 3. Verified Hex Codes (Transmitted MSB-First)
The following hex codes have been verified to work with the target TV when transmitted MSB-first:
- **Volume UP (+):** `0x40 0x04 0x01 0x00 0x04 0x05` (Checksum: 01^00^04 = 05)
- **Volume DOWN (-):** `0x40 0x04 0x01 0x00 0x84 0x85` (Checksum: 01^00^84 = 85)

## 4. UI Architecture
- **Framework:** Android Kotlin with XML-based layout built programmatically.
- **Debounce Mechanism:** To prevent the Android OS from firing multiple touch events in rapid succession (which could cause accidental volume double-jumps), a `500ms` programmatic debounce was added to the `setOnClickListener` of the UI buttons.

## 5. Development Guidelines (ECC)
As per the `Everything Claude Code (ECC)` guidelines (`AGENTS.md`):
- **Immutability:** Operations respect immutability.
- **Knowledge Capture:** This document acts as the definitive source of truth for the project's technical history and IR discoveries, ensuring future agents and developers do not need to rediscover the MSB-first and single-frame constraints.
