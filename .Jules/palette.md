## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardizing Navigation and Empty States
**Learning:** Initial screens often lacked explicit back navigation and feedback for empty data states, leading to dead-ends or confusing "blank" UI.
**Action:** Implement a centered `llEmptyState` with visual cues (emojis/icons) and clear CTAs for all list-based activities, and ensure `MaterialToolbar` always includes a theme-aware `homeAsUpIndicator` for consistent navigation.
