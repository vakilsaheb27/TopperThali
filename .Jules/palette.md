## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Empty State Pattern for Data Lists
**Learning:** A blank screen when no data is available creates uncertainty for the user. A centered empty state with a visual cue (emoji/icon) and clear instructions improves the "first-run" experience and makes the app feel more polished.
**Action:** Implement a centered `LinearLayout` with an informative emoji and guidance text when list data is empty, toggling its visibility in the main UI thread.
