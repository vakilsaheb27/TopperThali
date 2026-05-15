## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Avoid Obscuring Children with Root Content Descriptions
**Learning:** Setting a `contentDescription` on a root container (like a CardView) can cause screen readers to treat the entire container as a single interactive element, making all its children (text, buttons, etc.) unreachable.
**Action:** Prefer descriptive labels for individual interactive elements or ensure that a root description doesn't hide critical information like phone numbers or secondary actions.
