## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2026-04-30 - Discoverable Long-Press Actions for Accessibility
**Learning:** For Android list items with long-press functionality, screen reader users often miss the feature because it isn't explicitly announced. Providing a hint in the root view's `contentDescription` significantly improves discoverability.
**Action:** Include a hint like "Double tap and hold for more options" in the `contentDescription` of RecyclerView items that implement `onLongClickListener`.
