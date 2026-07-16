## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2024-05-15 - Inline Validation and Loading States in Forms
**Learning:** Generic Toast notifications for form validation are disconnected from the input context and can be missed by users. Similarly, lack of feedback during async operations (like DB saves) leads to double-submissions and uncertainty.
**Action:** Replace Toasts with TextInputLayout.error for contextual feedback. Use doOnTextChanged to clear errors dynamically. Always disable submit buttons and provide a "Loading..." state during background tasks to ensure a smooth, unambiguous interaction.
