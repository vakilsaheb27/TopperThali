## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardized Secondary Activity Headers
**Learning:** For secondary screens, users expect a consistent way to navigate back. Using theme-aware attributes like `?attr/homeAsUpIndicator` and providing clear `navigationContentDescription` ensures a seamless and accessible navigation experience.
**Action:** Always include a back navigation icon in secondary activity toolbars with an explicit content description and a functional navigation listener.
