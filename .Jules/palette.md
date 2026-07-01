## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Empty States and Navigation Affordance
**Learning:** Users can find blank screens confusing; an "empty state" with an icon and clear message provides better orientation. Additionally, relying solely on system back gestures can be less accessible than a visible back button in the toolbar.
**Action:** Implement empty state views (emoji + text) for all list-based screens and ensure `MaterialToolbar` includes a standard `navigationIcon` for explicit back navigation.
