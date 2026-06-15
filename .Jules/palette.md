## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-22 - Empty States and Standard Navigation
**Learning:** A missing empty state can make an app feel broken or empty without explanation. Similarly, missing back navigation on sub-screens breaks user expectations.
**Action:** Always implement a centered empty state for lists and ensure all activities (except the main dashboard) have a functional back button with an appropriate `contentDescription`.
