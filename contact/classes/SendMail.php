<?php

    namespace Compuflex\Contact\Classes;

    use Mail;
    use System\Models\MailTemplate;

    class SendMail {

        public static function sendNotification($properties, $post, $messageContent) {

            if(is_array($properties['mail_recipients'])) {

                # CUSTOM TEMPLATE
                $template = isset($properties['mail_template']) && $properties['mail_template'] != '' && MailTemplate::where('code', $properties['mail_template'])->count() ? $properties['mail_template'] : 'compuflex.contact::mail.notification';

                Mail::sendTo($properties['mail_recipients'], $template, [
                    'sender_name'   => $messageContent->sender_name,
                    'sender_email' => $messageContent->sender_email,
                    'message'   => $messageContent->message,
                    'date' => $messageContent->created_at
                ], function($message) use ($properties, $files) {

                    $message->subject($properties['mail_subject']);

                });

            }

        }

        public static function sendAutoResponse($properties, $post) {

            $to      = $post[$properties['mail_resp_field']];
            $from    = $properties['mail_resp_from'];
            $subject = $properties['mail_resp_subject'];

            if(filter_var($to, FILTER_VALIDATE_EMAIL) && filter_var($from, FILTER_VALIDATE_EMAIL)) {

                # CUSTOM TEMPLATE
                $template = isset($properties['mail_resp_template']) && $properties['mail_resp_template'] != '' && MailTemplate::where('code', $properties['mail_resp_template'])->count() ? $properties['mail_resp_template'] : 'martin.forms::mail.autoresponse';

                Mail::sendTo($to, $template, $post, function($message) use ($from, $subject) {
                    $message->from($from);
                    $message->subject($subject);
                });

            }

        }

    }

?>