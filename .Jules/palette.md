## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardizing Navigation Accessibility
**Learning:** Standard navigation icons (like the Material back arrow) require explicit content descriptions even when using standard attributes like `?attr/homeAsUpIndicator`. Screen readers may otherwise announce them generically or skip them.
**Action:** Always include `app:navigationContentDescription` when enabling navigation icons in `MaterialToolbar` to provide clear context for screen reader users.
