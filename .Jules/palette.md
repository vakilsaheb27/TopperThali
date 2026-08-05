## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standard Click vs Long Press for Options Discovery
**Learning:** Hidden options behind long-press gestures are highly undiscoverable. Placing core management options (Renew, View QR Code, Delete) under a standard card click/tap significantly increases feature discovery and provides a much better experience for both standard and screen-reader users.
**Action:** Always favor standard clicks/taps over long-press actions to open contextual action dialogs/menus for list items.
