## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standard Tap Gestures vs. Long-Press
**Learning:** Critical functions (like subscription renewal, deletion, and sharing QR codes) should be triggered via clear, standard tap gestures rather than hidden long-press actions, ensuring they are discoverable and accessible to all users.
**Action:** Implement standard tap gestures on list items to present action dialogs, keeping the interface clean while maintaining excellent discoverability.
