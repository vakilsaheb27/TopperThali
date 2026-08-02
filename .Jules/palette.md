## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standard Click over Obscure Long-Press for Options Dialogs
**Learning:** Long-press gestures are highly undiscoverable and unfriendly to screen readers. Standard clicks on item cards are significantly more discoverable and predictable for triggering options menus.
**Action:** Always trigger student options (recharge, deletion, viewing QR codes) via standard clicks/taps instead of long-press interactions.
