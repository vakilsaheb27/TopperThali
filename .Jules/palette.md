## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Persistent Form Validation & Submission Feedback
**Learning:** Transient 'Toast' messages for form validation are easily missed and lack context. 'TextInputLayout' error states provide persistent, context-specific feedback. Additionally, disabling the submission button during async operations prevents duplicate entries and provides implicit feedback.
**Action:** Replace 'Toast' validation with 'TextInputLayout' error states and disable 'Submit' buttons during background processing.
