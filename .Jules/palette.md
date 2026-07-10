## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Contextual Form Validation and Navigation
**Learning:** Generic global feedback like Toasts for form validation lack context and can be easily missed. Inline validation provides immediate, actionable feedback.
**Action:** Use `TextInputLayout.error` for form validation and `doOnTextChanged` to clear errors dynamically. Ensure every secondary screen has a clear navigation back path via a Toolbar.
