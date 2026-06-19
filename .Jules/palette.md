## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardizing Secondary Activity Navigation
**Learning:** Secondary activities in Android must provide a clear and accessible way to navigate back to the parent screen. Using `MaterialToolbar` with `homeAsUpIndicator` and a corresponding `navigationContentDescription` ensures consistency and accessibility.
**Action:** For all non-root activities, implement a `MaterialToolbar` with `app:navigationIcon="?attr/homeAsUpIndicator"` and `app:navigationContentDescription="Back"`, and handle the click event with `finish()`.
