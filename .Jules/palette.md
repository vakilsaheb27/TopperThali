## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Contextual Empty States for Lists
**Learning:** A blank screen when a list is empty often leads to user confusion, as it provides no feedback on whether the app is loading, broken, or simply lacks data.
**Action:** Implement centered empty state layouts (e.g., `llEmptyState`) with friendly imagery and descriptive text to guide users and confirm the current state of the application.
