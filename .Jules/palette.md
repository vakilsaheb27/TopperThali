## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardized Empty State Pattern
**Learning:** A centered layout with a large emoji, bold headline, and secondary instructional text provides a clear, friendly, and actionable empty state for list screens.
**Action:** Use a `LinearLayout` (ID `llEmptyState`) with gravity "center" and vertical orientation, containing an emoji (64sp), a bold title (18sp), and instructional subtext (14sp) to guide users when no data is present.
