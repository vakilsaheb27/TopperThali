## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Contextual Validation and Loading States
**Learning:** Generic `Toast` messages for form validation are easily missed by screen readers and provide poor visual context. Similarly, lack of button state changes during async operations can lead to duplicate submissions and user confusion.
**Action:** Use `TextInputLayout.error` for contextual validation feedback, clear errors dynamically as the user types, and always provide immediate feedback (like "Saving...") and disable buttons during asynchronous actions.
