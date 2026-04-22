## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Inline Form Validation with TextInputLayout
**Learning:** Using `Toast` for form validation is disjointed; `TextInputLayout.setError()` provides better context. However, errors must be cleared in real-time to avoid user frustration.
**Action:** Use `doOnTextChanged` on each `EditText` to clear its specific `TextInputLayout` error as soon as the user begins typing.
