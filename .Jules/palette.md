## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - MaterialToolbar for Intuitive Back Navigation
**Learning:** In mobile applications, relying solely on system gestures for navigation can be unintuitive. Providing a clear visual hierarchy with a Toolbar and a back navigation icon improves discoverability and orientation.
**Action:** Always implement a `MaterialToolbar` with a title and back navigation icon (using `?attr/homeAsUpIndicator`) for sub-screens to ensure a consistent and predictable navigation experience.
