## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2026-07-11 - Inline Validation UX with TextInputLayout
**Learning:** Using `Toast` for form validation provides poor UX as it's fleeting and doesn't specify which field needs attention. `TextInputLayout.setError` provides persistent, contextual feedback that is also more accessible to screen readers.
**Action:** Replace `Toast`-based validation with `TextInputLayout` error states and use `doOnTextChanged` to clear errors dynamically as the user corrects their input.
