## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Improving First-Run Experience with Empty States
**Learning:** A blank screen when a list is empty (like the student directory) provides no feedback or guidance to new users, potentially leading to confusion about how to proceed.
**Action:** Implement informative empty states with a clear call-to-action (e.g., "Tap + to add your first student") and a relevant emoji to provide visual context and encourage user interaction.
