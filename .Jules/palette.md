## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standard Tap Gestures vs Long-Press Discovery
**Learning:** Relying solely on long-press gestures (`setOnLongClickListener`) for primary item actions in RecyclerView cards creates poor feature discoverability, as standard tap gestures are the primary user expectation.
**Action:** Trigger options/management dialogs on standard click (`setOnClickListener`), while retaining long-press as an intuitive secondary gesture.
