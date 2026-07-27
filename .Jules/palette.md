## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-10-24 - Empty List State UX Pattern
**Learning:** Empty list interfaces benefit heavily from a visual placeholder with descriptive helper text (or call-to-actions) to prompt users on the next step rather than displaying a blank void.
**Action:** Always verify if a list has an empty state container (e.g., `layoutEmpty`) containing friendly emojis and dynamic toggling in the parent activity to maximize usability and prevent blank screens.
