## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Inline Form Validation with TextInputLayout
**Learning:** Using Toast for form validation errors is poor UX as it's transient and doesn't point to the specific field. TextInputLayout.setError() provides persistent, contextual feedback and is better for accessibility.
**Action:** Implement inline validation using TextInputLayout.setError() and clear errors dynamically with doOnTextChanged to provide a responsive and accessible form experience.
