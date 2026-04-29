## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standard Navigation and Empty States for Orientation
**Learning:** Users rely on standard navigation patterns (like the Material back arrow) and clear empty states to understand their current context and how to exit it. Non-standard icons or blank screens can lead to cognitive friction.
**Action:** Use `?attr/homeAsUpIndicator` for back navigation in toolbars and implement centered empty states with illustrative icons and clear instructions when lists are empty.
