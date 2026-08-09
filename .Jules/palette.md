## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Improving Interactive Discoverability via standard tap/click gestures
**Learning:** For item cards in list-based screens (like StudentListActivity), using obscure gestures such as long-press for critical options prevents users from discovering them. Standard tap/click gestures should be used instead to trigger options menus, showing the available actions immediately.
**Action:** Use standard tap/click gestures instead of long-press actions for item cards to trigger options dialogs (like student renewal, deletion, or viewing QR code) so critical interactive options are immediately discoverable.
