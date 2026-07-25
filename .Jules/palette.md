## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Form Validation Accessibility via TextInputLayout Errors
**Learning:** Toast-based form validation has poor usability and accessibility, as screen readers may miss ephemeral toast messages and users cannot easily re-read them. Using `TextInputLayout.error` keeps validation messages persistent and structurally associated with the input fields.
**Action:** Use `TextInputLayout.error` for form fields, validate all inputs simultaneously to avoid repetitive submissions, and clear errors dynamically as the user types using `doOnTextChanged` for an interactive, responsive experience.
