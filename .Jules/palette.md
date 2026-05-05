## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Standardizing Empty States
**Learning:** Users can be confused by a completely blank screen when a list is empty. Providing a centered empty state with a friendly emoji, clear message, and actionable next steps improves clarity and reduces perceived friction.
**Action:** Implement a standard empty state using a centered `LinearLayout` (ID `llEmptyState`) with an icon, title, and descriptive subtitle, and toggle it in the Activity logic based on data availability.
