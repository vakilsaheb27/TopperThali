## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standard Click vs. Obscure Long-Press Actions
**Learning:** Hidden long-press gestures on lists severely restrict feature discoverability and screen reader accessibility for major user operations.
**Action:** Always map primary settings, configurations, and profile menus to standard, recognizable click/tap gestures with interactive ripple feedback, and use tooltips or accessible dialog titles to guide the user.
