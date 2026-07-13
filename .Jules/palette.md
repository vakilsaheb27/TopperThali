## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2026-07-13 - Informative Empty States for List Views
**Learning:** Empty list screens without guidance can lead to user confusion and a "broken app" perception. A well-designed empty state provides both visual delight and a clear path forward.
**Action:** Implement descriptive empty states with illustrative icons (e.g., emojis) and helpful calls to action (e.g., "Tap + to add...") to guide users when no data is present.
