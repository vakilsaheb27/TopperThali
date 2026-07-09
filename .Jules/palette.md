## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Contextual Inline Validation over Generic Toasts
**Learning:** Using generic Toasts for form validation is a poor UX pattern as it lacks context and can be easily missed. Inline validation via `TextInputLayout.error` provides immediate, localized feedback that remains visible until addressed.
**Action:** Always prefer `TextInputLayout` error states for form validation, and pair them with text change listeners to clear the error state dynamically as the user correcting the input.
