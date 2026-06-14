## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Improving Form Validation UX with Persistent Feedback
**Learning:** Using `TextInputLayout` persistent error states is significantly more effective than transient `Toast` messages for form validation. It provides immediate, spatially relevant feedback that stays visible until the user corrects the input.
**Action:** Replace `Toast` validation with `TextInputLayout.error` and use `doAfterTextChanged` to clear errors in real-time as the user types.
