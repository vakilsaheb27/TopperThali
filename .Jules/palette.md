## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Theme Consistency and Empty States
**Learning:** Hardcoding hex values prevents proper Dark Mode support and causes visual inconsistency across the app. Centered empty states with icons and descriptive text significantly improve onboarding UX.
**Action:** Use theme attributes like `?attr/colorPrimary` and `?attr/colorOnSurface` for all UI components. Always implement an `llEmptyState` pattern for list-based screens.
