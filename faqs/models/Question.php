<?php namespace Compuflex\Faqs\Models;

use Model;

/**
 * Model
 */
class Question extends Model
{
    use \October\Rain\Database\Traits\Validation;

    use \October\Rain\Database\Traits\SoftDelete;

    protected $dates = ['deleted_at'];

    /*
     * Validation
     */
    public $rules = [
    ];

    /**
     * @var string The database table used by the model.
     */
    public $table = 'compuflex_faqs_questions';

    public $hasMany = [
      'answers' =>  'Compuflex\Faqs\Models\Answer'
    ];
    
    public $belongsTo = [
      'company' => ['Compuflex\Company\Models\Company']
    ];
}
