## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Empty State and Navigation Consistency
**Learning:** A blank screen when no data is available is confusing. A centered empty state with a friendly emoji and actionable instructions improves the first-run experience. Consistent back navigation in Toolbars using `?attr/homeAsUpIndicator` ensures a predictable user flow.
**Action:** Always implement a dedicated empty state for list-based screens and ensure all secondary activities have a back button in the toolbar.
