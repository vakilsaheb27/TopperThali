## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Helpful Empty States for Data Lists
**Learning:** A blank screen when no data is present (like an empty student directory) can be confusing and makes the app feel broken or unguided.
**Action:** Implement descriptive empty states with an icon/emoji and a clear call-to-action (e.g., "Tap the + button to add your first student") to provide immediate feedback and guide the user.
