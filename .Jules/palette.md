## 2025-05-14 - Personalized Accessibility Labels for List Items
**Learning:** For list items with generic icon buttons (like WhatsApp or Delete), static accessibility labels are insufficient for screen reader users as they lack context.
**Action:** Always provide dynamic, personalized `contentDescription` for interactive elements in adapters (e.g., "Send WhatsApp reminder to [Name]") to improve screen reader navigation and clarity.

## 2025-05-15 - Instructional Empty States for Data-Driven Screens
**Learning:** In utility-focused apps, a blank screen can be mistaken for a loading failure or a bug. Instructional empty states (emoji + clear CTA text) significantly improve user onboarding and confidence.
**Action:** Implement a descriptive empty state for all list-based activities that toggles visibility with the main content based on data availability.
