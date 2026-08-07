## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Enhancing Feature Discoverability with Click Gestures & Tactical Ripple Feedback
**Learning:** Obscure long-press gestures on list items are highly non-discoverable. Replacing them with standard click/tap gestures to invoke contextual option menus dramatically improves feature accessibility. Additionally, interactive items must feature standard ripple/hover states (`selectableItemBackground`) and dynamic accessibility labels on child elements/dialog views (e.g. "QR Code for [Student Name]") to maintain continuous screen reader context.
**Action:** Default to standard tap/click gestures instead of long-presses for critical options, always pair with tactile ripple feedback, and ensure dynamic dialog images are thoroughly labeled.
