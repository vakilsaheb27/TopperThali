## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardizing Empty States and Theme-Aware Styling
**Learning:** In a Material 3 app, hardcoded hex values for primary components (Toolbars, FABs) hinder dark mode support and theme consistency. Additionally, providing an empty state with helpful imagery and actionable text significantly improves the "first-run" experience.
**Action:** Use `?attr/colorPrimary` and `?attr/colorOnPrimary` for key UI elements and always implement a centered `llEmptyState` layout to guide users when data is missing.
