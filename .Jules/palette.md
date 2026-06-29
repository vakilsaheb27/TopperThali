## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-14 - Interactive Search Accessibility & Persistence
**Learning:** When implementing search in long lists, maintaining the search filter across data reloads (e.g., on activity resume) ensures a seamless user experience and prevents cognitive disorientation.
**Action:** Re-apply the search filter in the UI's data loading method (like `onResume` or `loadData`) if the search input is not empty.
