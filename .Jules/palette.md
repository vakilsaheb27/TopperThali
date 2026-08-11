## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Tap/Click Interaction for Contextual Actions over Long-Press
**Learning:** Long-press interactions on mobile list items are highly undiscoverable, causing users to miss core features like viewing QR codes or editing items.
**Action:** Always use standard single-tap/click gestures for list item cards to open a bottom sheet or options menu containing all contextual actions (e.g., Renew, View QR, Delete) instead of relying on long-press.
