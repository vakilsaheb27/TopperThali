## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Contextual Form Validation with TextInputLayout
**Learning:** Using transient Toast messages for form validation is poor UX as it lacks context and can be easily missed. TextInputLayout error states provide persistent, field-specific feedback that is more accessible and intuitive.
**Action:** Replace validation Toasts with TextInputLayout error states and use `doAfterTextChanged` to clear them dynamically as the user types.
