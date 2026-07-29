## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2026-04-27 - Gestures & Discoverability vs Screen Readers
**Learning:** Long-press interactions for critical/primary options on listing screens are obscure and hard to discover, especially on mobile interfaces. Replacing these with standard clicks/taps significantly improves discoverability while allowing screen readers to easily announce and navigate option dialogues.
**Action:** Always prefer standard click/tap gestures over long-press for primary list-item action triggers, and ensure any dynamically shown dialog views (like QR Codes) provide dynamic, personalized `contentDescription` for screen-reader readability.
