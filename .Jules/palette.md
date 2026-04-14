## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Focused Inline Validation Clearing
**Learning:** Clearing all form errors simultaneously when any field is edited can be confusing and may hide valid errors in other fields.
**Action:** Use individual `TextWatcher` listeners for each `TextInputLayout` to clear error states specifically for the field being edited, maintaining the visibility of errors in other fields.
