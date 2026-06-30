## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardizing Navigation Accessibility
**Learning:** MaterialToolbars with navigation icons (back buttons) are often overlooked for accessibility. Without a `navigationContentDescription`, screen reader users only hear "Button" without context, making it difficult to understand how to exit a screen.
**Action:** Always include `app:navigationContentDescription="Back"` when using `app:navigationIcon` in a `MaterialToolbar` to provide immediate context for the navigation action.
