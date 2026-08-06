## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standard Click and QR Code Discoverability
**Learning:** For critical options (like View QR Code, Renewal, Deletion), long-press gestures are not easily discoverable by normal users. Furthermore, static content descriptions on dialog QR codes lack personalized context for screen readers.
**Action:** Migrate item options to standard click listeners, and always dynamically set descriptive `contentDescription` strings on generated QR code ImageViews (e.g., "QR Code for [Student Name]") in options and registration screens.
