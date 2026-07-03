## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardizing Navigation and Theme Attributes
**Learning:** Inconsistent navigation patterns (e.g., missing back buttons on secondary screens) and hardcoded colors can make an app feel unpolished. Using theme attributes like `?attr/homeAsUpIndicator` and `?attr/colorPrimary` ensures visual consistency across themes (light/dark) and system versions.
**Action:** Always use theme attributes for UI components and include explicit, labeled back navigation in MaterialToolbars for secondary activities.
