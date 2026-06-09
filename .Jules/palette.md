## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Inline Form Validation with TextInputLayout
**Learning:** Transient Toast messages for form validation are easily missed and don't provide persistent context. TextInputLayout error states provide immediate, pinned feedback.
**Action:** Replace `Toast` validation with `TextInputLayout.error`. Use `doAfterTextChanged` to clear errors as soon as the user starts correcting the input.
