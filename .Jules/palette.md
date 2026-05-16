## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2026-05-16 - Contextual Empty States and Navigation Consistency
**Learning:** Sub-activities without explicit back navigation and blank list screens create a "dead-end" feeling and increase user cognitive load.
**Action:** Always implement a MaterialToolbar with a navigation icon (back arrow) in sub-activities and provide descriptive empty states with clear calls-to-action to guide users.
