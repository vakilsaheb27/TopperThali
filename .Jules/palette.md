## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Descriptive Empty States and Standard Navigation
**Learning:** Empty screens without guidance create friction for new users. Standardizing back navigation with accessibility descriptions ensures a predictable and inclusive user flow.
**Action:** Implement empty states with clear calls-to-action (e.g., "Tap + to add...") and ensure all secondary screens have back navigation icons with explicit `contentDescription="Back"`.
